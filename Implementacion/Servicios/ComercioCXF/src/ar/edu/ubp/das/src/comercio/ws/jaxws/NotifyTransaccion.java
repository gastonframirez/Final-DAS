
package ar.edu.ubp.das.src.comercio.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.2.4
 * Mon Feb 04 01:32:54 ART 2019
 * Generated source version: 3.2.4
 */

@XmlRootElement(name = "notifyTransaccion", namespace = "http://ws.comercio.src.das.ubp.edu.ar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notifyTransaccion", namespace = "http://ws.comercio.src.das.ubp.edu.ar/", propOrder = {"arg0", "arg1", "arg2", "arg3", "arg4", "arg5", "arg6", "arg7", "arg8", "arg9", "arg10"})

public class NotifyTransaccion {

    @XmlElement(name = "arg0")
    private java.lang.String arg0;
    @XmlElement(name = "arg1")
    private java.lang.String arg1;
    @XmlElement(name = "arg2")
    private java.lang.String arg2;
    @XmlElement(name = "arg3")
    private java.lang.String arg3;
    @XmlElement(name = "arg4")
    private java.lang.String arg4;
    @XmlElement(name = "arg5")
    private java.lang.Integer arg5;
    @XmlElement(name = "arg6")
    private java.lang.String arg6;
    @XmlElement(name = "arg7")
    private java.lang.String arg7;
    @XmlElement(name = "arg8")
    private java.lang.Integer arg8;
    @XmlElement(name = "arg9")
    private java.lang.Float arg9;
    @XmlElement(name = "arg10")
    private java.lang.Float arg10;

    public java.lang.String getArg0() {
        return this.arg0;
    }

    public void setArg0(java.lang.String newArg0)  {
        this.arg0 = newArg0;
    }

    public java.lang.String getArg1() {
        return this.arg1;
    }

    public void setArg1(java.lang.String newArg1)  {
        this.arg1 = newArg1;
    }

    public java.lang.String getArg2() {
        return this.arg2;
    }

    public void setArg2(java.lang.String newArg2)  {
        this.arg2 = newArg2;
    }

    public java.lang.String getArg3() {
        return this.arg3;
    }

    public void setArg3(java.lang.String newArg3)  {
        this.arg3 = newArg3;
    }

    public java.lang.String getArg4() {
        return this.arg4;
    }

    public void setArg4(java.lang.String newArg4)  {
        this.arg4 = newArg4;
    }

    public java.lang.Integer getArg5() {
        return this.arg5;
    }

    public void setArg5(java.lang.Integer newArg5)  {
        this.arg5 = newArg5;
    }

    public java.lang.String getArg6() {
        return this.arg6;
    }

    public void setArg6(java.lang.String newArg6)  {
        this.arg6 = newArg6;
    }

    public java.lang.String getArg7() {
        return this.arg7;
    }

    public void setArg7(java.lang.String newArg7)  {
        this.arg7 = newArg7;
    }

    public java.lang.Integer getArg8() {
        return this.arg8;
    }

    public void setArg8(java.lang.Integer newArg8)  {
        this.arg8 = newArg8;
    }

    public java.lang.Float getArg9() {
        return this.arg9;
    }

    public void setArg9(java.lang.Float newArg9)  {
        this.arg9 = newArg9;
    }

    public java.lang.Float getArg10() {
        return this.arg10;
    }

    public void setArg10(java.lang.Float newArg10)  {
        this.arg10 = newArg10;
    }

}

