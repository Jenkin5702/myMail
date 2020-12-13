import com.alibaba.fastjson.JSON;
import com.tju.myMailServer.entities.Mail;
import com.tju.myMailServer.entities.MailTypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Mail mail=new Mail(
                MailTypes.SENT,
                "TestMail",
                "from@test.com",
                "to@test.com",
                new Date(),
                null,
                "Hello!\tTest \ncontent."
        );
        String packet=mail.toString();
        System.out.println(packet);
        Mail recover = JSON.parseObject(packet, Mail.class);
        System.out.println(recover.toString());
        System.out.println(recover.getContent());

        List<Mail> mailList=new ArrayList<>();
        mailList.add(mail);
        mailList.add(recover);
        System.out.println(JSON.toJSONString(mailList));

        List<Mail> recoverList=JSON.parseArray(JSON.toJSONString(mailList), Mail.class);
    }
}
