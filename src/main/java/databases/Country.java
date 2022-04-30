package databases;

import lombok.Data;

@Data
public class Country {
    private int id;
    private String name;
    private int code;
    private String continent;
}
