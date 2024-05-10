package com.takdanai.courseware.controllers.payload.messages;

import com.takdanai.courseware.entities.Student;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentMessage {
    public String body;
    public Student student;
}
