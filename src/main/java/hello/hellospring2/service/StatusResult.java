package hello.hellospring2.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusResult{
    private int status;

    public StatusResult(int status){
        this.status = status;
    }
}
