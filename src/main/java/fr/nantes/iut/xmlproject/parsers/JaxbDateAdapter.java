package main.java.fr.nantes.iut.xmlproject.parsers;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ughostephan on 02/03/2016.
 */
public class JaxbDateAdapter extends XmlAdapter<String, Date> {

    final DateFormat df = new SimpleDateFormat("yy-MM-dd");

    public Date unmarshal(String date) throws Exception {
        return df.parse(date);
    }

    public String marshal(Date date) throws Exception {
        return df.format(date);
    }
}