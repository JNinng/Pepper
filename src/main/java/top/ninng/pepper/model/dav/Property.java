package top.ninng.pepper.model.dav;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.w3c.dom.Element;
import top.ninng.pepper.utils.ElementConverter;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 10:49
 * @Version 1.0
 */
public class Property {

    private Element property;

    public Element getProperty() {
        return property;
    }

    public void setProperty(Element property) {
        this.property = property;
    }

    public static class PropertyConverter implements Converter<Property> {
        @Override
        public Property read(InputNode node) throws Exception {
            Property property = new Property();
            InputNode childNode = node.getNext();
            if (childNode != null) {
                property.setProperty(ElementConverter.read(childNode));
            }
            return property;
        }

        @Override
        public void write(OutputNode node, Property value) throws Exception {
            ElementConverter.write(node, value.property);
        }
    }
}
