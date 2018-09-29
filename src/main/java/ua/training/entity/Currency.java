package ua.training.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Currency {
    private int r030;
    private String txt;
    private double rate;
    private String cc;
    private LocalDate exchangedate;

    public Currency(int r030, String txt, double rate, String cc, LocalDate exchangedate) {
        this.r030 = r030;
        this.txt = txt;
        this.rate = rate;
        this.cc = cc;
        this.exchangedate = exchangedate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return r030 == currency.r030 &&
                Double.compare(currency.rate, rate) == 0 &&
                Objects.equals(txt, currency.txt) &&
                Objects.equals(cc, currency.cc) &&
                Objects.equals(exchangedate, currency.exchangedate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(r030, txt, rate, cc, exchangedate);
    }

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
