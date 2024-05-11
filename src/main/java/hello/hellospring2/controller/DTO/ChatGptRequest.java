package hello.hellospring2.controller.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ChatGptRequest {
    private String model;
    private List<Message> messages = new ArrayList<>();
}
