package net.gfu.wicket.cheesr.webapp.tables;

import net.gfu.wicket.backend.BOServices;
import net.gfu.wicket.backend.bo.Cheese;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;

public class CheeseTableDefinition implements Serializable{


    public List<PropertyColumn> columns() {
        return Arrays.asList(
                createColumn("Name","name"),
                createColumn("Description", "description"),
                priceCol()
        );
    }

    public SortableDataProvider<Cheese,String> provider() {
        return new Provider();
    }

    PropertyColumn<Cheese,String> createColumn(String title, String prop) {
        return new PropertyColumn<>((IModel<String>) () -> title,prop,prop);

    }

    private PropertyColumn<Cheese,String> priceCol(){
        return new PropertyColumn<Cheese,String>((IModel<String>) () -> "Price","price","price"){
            public IModel<?> getDataModel(IModel<Cheese> rowModel) {
               return (IModel<String>) () -> NumberFormat.getCurrencyInstance().format(rowModel.getObject().getPrice());
            }
        };
    }

    static class Provider extends SortableDataProvider<Cheese,String> {


        private List<Cheese> list = new ArrayList<>();
        private SortableDataProviderComparator comparator = new SortableDataProviderComparator();

        public Provider() {
            setSort("name", SortOrder.ASCENDING);

            list.addAll(BOServices.get().allCheeses());
        }

        public Iterator<Cheese> iterator(final long first, final long count) {
            List<Cheese> newList = new ArrayList<Cheese>(list);
            Collections.sort(newList, comparator);
            return newList.subList((int)first, (int)(first + count)).iterator();
        }

        public long size() {
            return list.size();
        }

        @Override
        public IModel<Cheese> model(Cheese cheese) {
            return (IModel<Cheese>) () -> cheese;
        }

        @Override
        public void detach() {

        }

        class SortableDataProviderComparator implements Comparator<Cheese>, Serializable {
            public int compare(final Cheese o1, final Cheese o2) {
                PropertyModel<Comparable> model1 = new PropertyModel<>(o1, getSort().getProperty());
                PropertyModel<Comparable> model2 = new PropertyModel<>(o2, getSort().getProperty());

                int result = model1.getObject().compareTo(model2.getObject());

                if (!getSort().isAscending()) {
                    result = -result;
                }

                return result;
            }

        }

    }
}
