
package ar.edu.ubp.das.src.comercio.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.2.4
 * Fri Jan 25 01:14:54 ART 2019
 * Generated source version: 3.2.4
 */

@XmlRootElement(name = "getOfertas", namespace = "http://ws.comercio.src.das.ubp.edu.ar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOfertas", namespace = "http://ws.comercio.src.das.ubp.edu.ar/")

public class GetOfertas {

    @XmlElement(name = "arg0")
    private java.lang.String arg0;

    public java.lang.String getArg0() {
        return this.arg0;
    }

    public void setArg0(java.lang.String newArg0)  {
        this.arg0 = newArg0;
    }

}

