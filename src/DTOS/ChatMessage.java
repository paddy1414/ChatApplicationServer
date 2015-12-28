/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOS;

import java.util.Objects;

/**
 *
 * @author Patrick
 */
public class ChatMessage {
    
      private String from, to, subject, body;

    public ChatMessage(String from, String to, String body, String subject)
    {
        this.from = from;
        this.to = to;
        this.body = body;
        this.subject = subject;
    }

    public String getFrom()
    {
        return from;
    }

    public String getTo()
    {
        return to;
    }

    public String getBody()
    {
        return body;
    }

    public String getSubject()
    {
        return subject;
    }

    public String toString()
    {
        return "Message [from=" + from + ", to=" + to + ", subject=" + subject + ", body=" + body + "]";
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.from);
        hash = 29 * hash + Objects.hashCode(this.to);
        hash = 29 * hash + Objects.hashCode(this.subject);
        hash = 29 * hash + Objects.hashCode(this.body);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final ChatMessage other = (ChatMessage) obj;
        if (!Objects.equals(this.from, other.from))
        {
            return false;
        }
        if (!Objects.equals(this.to, other.to))
        {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject))
        {
            return false;
        }
        if (!Objects.equals(this.body, other.body))
        {
            return false;
        }
        return true;
    }

    public String getViewFormat()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("To: " + to);
        sb.append("\nFrom: " + from);
        sb.append("\nSubject: " + subject);
        sb.append("\nMessage Body: " + body);

        return sb.toString();
    }
    
}
