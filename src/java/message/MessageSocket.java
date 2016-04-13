/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.core.Response;

/**
 *
 * @author c0660563
 */
@ApplicationScoped
@ServerEndpoint("/chat")
public class MessageSocket {

    MessageController messages = new MessageController();

    @OnMessage
    public Response onMessage(String str) {
        Response result = null;
        JsonObject json = Json.createReader(new StringReader(str)).readObject();
        try {
            if (json.containsKey("getAll")) {
                JsonArrayBuilder jsonArray = Json.createArrayBuilder();
                for (Message msg : messages.getAll()) {
                    jsonArray.add(msg.toJSON());
                }
                result = Response.ok(jsonArray.build().toString()).build();

            } else if (json.containsKey("getById")) {
                JsonObject jsonRes = messages.getById(json.getInt("id")).toJSON();
                result = Response.ok(jsonRes).build();

            } else if (json.containsKey("getFromTo")) {
                JsonArrayBuilder jsonArray = Json.createArrayBuilder();
                for (Message msg : messages.getFromTo(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(json.getString("startDate")), 
                        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(json.getString("endDate")))) {
                    jsonArray.add(msg.toJSON());
                }
                result = Response.ok(jsonArray.build().toString()).build();

            } else if (json.containsKey("post")) {
                messages.add(json.getString("title"), json.getString("contents"), json.getString("author"),
                        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(json.getString("senttime")));
                JsonArrayBuilder jsonArray = Json.createArrayBuilder();
                for (Message msg : messages.getAll()) {
                    jsonArray.add(msg.toJSON());
                }
                result = Response.ok(jsonArray.build().toString()).build();

            } else if (json.containsKey("put")) {
                messages.update(json.getInt("id"), json.getString("title"), json.getString("contents"));
                result = Response.ok().build();

            } else if (json.containsKey("delete")) {
                messages.delete(json.getInt("id"));
                result = Response.ok().build();

            }
        } catch (Exception ex) {
            result = Response.status(500).build();
        }
        return result;
    }

    @OnOpen
    public Response onOpen() {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        for (Message msg : messages.getFromTo(new Date(), cal.getTime())) {
            jsonArray.add(msg.toJSON());
        }
        return Response.ok(jsonArray.build().toString()).build();
    }
}
