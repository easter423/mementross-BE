package hello.hellospring2.controller.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DalleResponse {
    private long created;
    private List<data> data;

    @Data
    public static class data {
        private String revised_prompt;
        private String url;
    }
}
