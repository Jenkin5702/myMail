# myMail
# 基于winsock的电子邮件系统

## Classes

```
mail{
	status; // unread/read, script/sent
	title;
	user_address_from;
	user_name_from;
	content; // keep formatted
	time; // YYYY-MM-DD, HH:MM:SS, DAY
	suppliments;
}

user{
	mail_address;
	login_password;
}
```

## Functions

```
// User functions
bool login(mail_address, password);
void create_mail();
void list_script();
void list_received();
void list_new_received();
void list_sent();
void delete_mail();
void set_user_to(user_address_to);
void set_title();
void save_as_script();
void send();
void edit_content();
void add_suppliemts();
void get_mail();
void get_suppliment();

// System functions

```

## Client

```

```



## Server

```

```

