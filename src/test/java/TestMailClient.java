import com.alibaba.fastjson.JSON;
import com.tju.myMailServer.client.MailClient;
import com.tju.myMailServer.entities.Mail;
import com.tju.myMailServer.entities.MailTypes;

import java.util.Date;
import java.util.List;

public class TestMailClient {


    public static void main(String[] args) {

        final String from="from@my.com";
        final String to="to@my.com";

        Mail mail1 = new Mail("script", from, to, new Date().getTime(), "", "script content");
        Mail mail2 = new Mail("sent", from, to, new Date().getTime(), "", "sent content");
        Mail mail3 = new Mail("unread", from, to, new Date().getTime(), "", "unread content");
        Mail mail4 = new Mail("to_delete", from, to, new Date().getTime(), "", "to delete content");
        Mail mail5 = new Mail("received", from, to, new Date().getTime(),"", "received content");

        MailClient client=new MailClient();
        client.saveScript(mail1);
        client.sendMail(mail2);
        client.sendMail(mail3);
        client.sendMail(mail4);
        client.sendMail(mail5);

        List<Mail> list1=client.listScript(from);
        System.out.println(JSON.toJSONString(list1));
        List<Mail> list2=client.listUnread(from);
        System.out.println(JSON.toJSONString(list2));
        List<Mail> list3=client.listSent(from);
        System.out.println(JSON.toJSONString(list3));
        List<Mail> list4=client.listReceived(from);
        System.out.println(JSON.toJSONString(list4));
        System.out.println(JSON.toJSONString(client.deleteMail(mail4)));
    }
}
