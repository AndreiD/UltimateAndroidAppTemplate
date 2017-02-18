package com.andrei.template.data.models;


import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Generated;
import io.objectbox.annotation.Id;

@Entity
public class Note {

  @Id
  private long id;

  private String text;
  private String comment;
  private Date date;


  @Generated(hash = 1029219451)
  public Note(long id, String text, String comment, Date date) {
    this.id = id;
    this.text = text;
    this.comment = comment;
    this.date = date;
  }

  @Generated(hash = 1272611929)
  public Note() {
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getComment() {
    return this.comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Date getDate() {
    return this.date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override public String toString() {
    return "Note{" +
        "id=" + id +
        ", text='" + text + '\'' +
        ", comment='" + comment + '\'' +
        ", date=" + date +
        '}';
  }
}