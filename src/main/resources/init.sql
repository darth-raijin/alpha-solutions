create table users
(
    personnelNumber int(10) auto_increment,
    name            varchar(255) null,
    email           varchar(255) null,
    address         varchar(255) null,
    postalCode      varchar(20)  null,
    country         varchar(255) null,
    phoneNumber     varchar(255) null,
    password        varchar(255) null,
    city            varchar(50)  null,
    constraint Users_email_uindex
        unique (email),
    constraint Users_personnelNumber_uindex
        unique (personnelNumber)
);

alter table users
    add primary key (personnelNumber);

create table projects
(
    projectID       int(10) auto_increment,
    name            varchar(255)     null,
    description     varchar(255)     null,
    isCompleted     bit default b'0' not null,
    personnelNumber int(10)          not null,
    deadline        varchar(10)      null,
    constraint Projects_projectsID_uindex
        unique (projectID),
    constraint Projects_users_personnelNumber_fk
        foreign key (personnelNumber) references users (personnelNumber)
);

alter table projects
    add primary key (projectID);

create table subprojects
(
    subprojectID int(10) auto_increment,
    name         varchar(255)     null,
    description  varchar(255)     null,
    isCompleted  bit default b'0' not null,
    projectsID   int(10)          not null,
    constraint Subprojects_subprojectID_uindex
        unique (subprojectID),
    constraint Subprojects_projects_projectsID_fk
        foreign key (projectsID) references projects (projectID)
);

alter table subprojects
    add primary key (subprojectID);

create table tasks
(
    taskID       int(10) auto_increment,
    name         varchar(255)     null,
    description  varchar(255)     null,
    isCompleted  bit default b'0' not null,
    subprojectID int(10)          not null,
    constraint tasks_taskID_uindex
        unique (taskID),
    constraint tasks_subprojects_subprojectID_fk
        foreign key (subprojectID) references subprojects (subprojectID)
);

alter table tasks
    add primary key (taskID);

create table subtasks
(
    subtaskID   int(10) auto_increment,
    name        varchar(255)     null,
    description varchar(255)     null,
    time        double(10, 0)    null,
    isCompleted bit default b'0' not null,
    taskID      int(10)          not null,
    constraint subtasks_subtaskID_uindex
        unique (subtaskID),
    constraint subtasks_tasks_taskID_fk
        foreign key (taskID) references tasks (taskID)
);

alter table subtasks
    add primary key (subtaskID);

INSERT INTO users (name, email, address, postalCode, country, phoneNumber, password, city, personnelNumber) 
values ('Test Admin', 'mail@gmail.com', 'Address', 'E14 5AB', 'England', '+4542489994', '123', 'London', 1);

INSERT INTO projects(name, description, personnelNumber, deadline) values ('Test22','Teest',1,'25/12/2021');

