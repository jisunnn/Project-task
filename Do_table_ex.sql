CREATE TABLE D_MEMBER(
    id varchar2(10) primary key,
    password varchar2(15),
    name varchar2(10),
    mail varchar2(50) not null,
    nick varchar2(10) not null,
    rid varchar2(10),
    point number(7) default 1000,
    prefer varchar2(10),
    spare varchar2(20)
);

CREATE TABLE D_CHALLENGE(
    seq number(5) primary key,
    title varchar2(200),
    regdate date
    id varchar2(10),
    sdate date,
    term number(2),
    content varchar2(3000),
    thumbnail varchar2(100)
    cnt number(5),
    state varchar2(10),
    spare varchar2(20)
);

CREATE TABLE PILEDATA(
    cno number(5) primary key REFERENCES d_challenge(seq),
    entry varchar2(10),
    attend number(3),
    term number(2),
    spare varchar2(20)
);

CREATE TABLE RANKING(
    id varchar2(10),
    rank number(3),
    nick varchar2(10),
    point number(7)
);

CREATE TABLE REPLY(
    seq number(5) primary key,
    root number(5),
    indent number(5),
    step number(5),
    id varchar2(10),
    content varchar2(200),
    regdate timestamp,
    report varchar2(20),
    spare varchar2(20)
);

CREATE TABLE NOTICE(
    seq number(5) primary key,
    title varchar2(200),
    regdate timestamp,
    content varchar2(3000),
    id varchar2(10),
    cnt number(5) 
);
    
CREATE TABLE ASK(
    seq number(5) primary key,
    title varchar2(200),
    regdate timestamp,
    content varchar2(3000),
    id varchar2(10),
    root number(5),
    indent number(5),
    step number(5)
);