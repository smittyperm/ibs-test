package main.ru.ibs.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Claim {
    private Long id;
    private Integer number;
    private String name;
    private Long from;
    private Long to;
    private Long status;

    public Claim() {
    }

    public Claim(Long id, Integer number, String name, Long from, Long to, Long status) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.from = from;
        this.to = to;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("number=").append(number).append(";");
        builder.append("name=").append(name).append(";");
        builder.append("from=").append(from).append(";");
        builder.append("to=").append(to).append(";");
        builder.append("status=").append(status).append(";");
        return builder.toString();
    }
}
