package org.naim.doctoo.model;

import java.io.Serializable;
import java.util.Date;

public class AppointmentCompositeKey implements Serializable {
    private Docteur docteur;
    private Date date;
}