/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author c0660563
 */
@ApplicationScoped
public class MessageController {
    private List<Message> messageList;

    public MessageController() {
        messageList = new ArrayList<>();
    }
    
    public List<Message> getAll() {
        return messageList;
    }
    
    public Message getById(int id) {
        for (Message m : messageList) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }
    
    public List<Message> getFromTo(Date from, Date to) {
        List<Message> result = new ArrayList<>();
        for (Message m : messageList) {
            if (m.getSentTime().after(from) && m.getSentTime().before(to)) {
                result.add(m);
            }
        }
        return result;
    }
    
    public int add(String title, String contents, String author, Date sentTime) {
        Message m = new Message();
        m.setId(messageList.size() + 1);
        m.setTitle(title);
        m.setContents(contents);
        m.setAuthor(author);
        m.setSentTime(sentTime);
        messageList.add(m);
        
        return m.getId();
    }
    
    public void update(int id, String title, String contents) {
        for (Message m : messageList) {
            if(m.getId() == id) {
                m.setTitle(title);
                m.setContents(contents);
            }
        }
    }
    
    public void delete(int id) {
        for (Message m : messageList) {
            if(m.getId() == id) {
                messageList.remove(m);
            }
        }
    }
}
