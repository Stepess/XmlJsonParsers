package ua.training.entity;

import java.time.LocalDate;

public class Currency {
    private int r030;
    private String txt;
    private double rate;
    private String cc;
    private LocalDate exchangedate;

    public String getTxt() {
        return txt;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "r030=" + r030 +
                ", txt='" + txt + '\'' +
                ", rate=" + rate +
                ", cc='" + cc + '\'' +
                ", exchangedate=" + exchangedate +
                '}';
    }
}
