package net.gfu.wicket.cheesr.webapp.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import net.gfu.wicket.backend.bo.CheeseAttribute;
import net.gfu.wicket.cheesr.webapp.components.CheeseAttributeList;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class CheeseAttributeComparator implements Comparator<CheeseAttribute> {
    Configuration conf = Configuration.get();
    HashMap<String, Integer> known = new HashMap<>();

    public CheeseAttributeComparator() {
        for (int i = 0; i < conf.getCheese_attribute_order().size(); i++) {
            known.put(conf.getCheese_attribute_order().get(i), i);
        }
    }

    @Override
    public int compare(CheeseAttribute o1, CheeseAttribute o2) {
        if (o1 == null || o2 == null) {
            return 0;
        } else if (known.containsKey(o1.getAttributeName()) && known.containsKey(o2.getAttributeName())) {
            return known.get(o1.getAttributeName()).compareTo(known.get(o2.getAttributeName()));
        } else if (known.containsKey(o1.getAttributeName())) {
            return -1;
        } else if (known.containsKey(o2.getAttributeName())) {
            return 1;
        } else {
            return o1.getAttributeName().compareTo(o2.getAttributeName());
        }
    }


    /// Parsing der YAML-Konfiguration
    private static class Configuration {
        private List<String> cheese_attribute_order;

        public List<String> getCheese_attribute_order() {
            return cheese_attribute_order;
        }

        public void setCheese_attribute_order(List<String> cheese_attribute_order) {
            this.cheese_attribute_order = cheese_attribute_order;
        }

        public static Configuration get() {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            try {
                return mapper.readValue(CheeseAttributeList.class.getResourceAsStream("/config.yaml"), Configuration.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
