/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author rt_pr
 */
public class Message {
   
    private int id;
    private String title;
    private String contents;
    private String author;
    private Date sentTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }
    
     //{ "id" : 1, "title" : "Sample Title", "contents" : "Some sample contents for the message.", 
//"author" : "A. Sample", "senttime" : "2016-03-31T13:24:11.135Z" }
    @Override
    public String toString() {
        return "{ \"id\" : " + id + 
                ", \"title\" : \"" + title +
                "\", \"contents\" : \"" + contents +
                "\", \"author\" : \"" + author +
                "\", \"senttime\" : \"" + sentTime + "\" }";
    }
    
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("id", id)
                .add("title", title)
                .add("contents", contents)
                .add("author", author)
                .add("senttime", sentTime.toString())
                .build();
    }
}
