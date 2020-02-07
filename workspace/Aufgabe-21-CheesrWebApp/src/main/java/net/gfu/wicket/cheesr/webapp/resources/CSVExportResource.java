package net.gfu.wicket.cheesr.webapp.resources;

import net.gfu.wicket.backend.bo.Cheese;
import net.gfu.wicket.cheesr.webapp.tables.CheeseTableDefinition;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.export.CSVDataExporter;
import org.apache.wicket.extensions.markup.html.repeater.data.table.export.IExportableColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.request.resource.AbstractResource;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CSVExportResource extends AbstractResource {
    @Override
    protected ResourceResponse newResourceResponse(Attributes attributes) {
        ResourceResponse resourceResponse = new ResourceResponse();
        resourceResponse.setContentType("text/csv");
        resourceResponse.setTextEncoding("utf-8");
        resourceResponse.setFileName("cheese.csv");

        resourceResponse.setWriteCallback(new WriteCallback()
        {
            @Override
            public void writeData(Attributes attributes) throws IOException
            {
                OutputStream outputStream = attributes.getResponse().getOutputStream();
                CheeseTableDefinition definition = new CheeseTableDefinition();

                CSVDataExporter exporter = new CSVDataExporter();
                List<IExportableColumn<Cheese,?>> columns = new ArrayList<>();

                for(PropertyColumn<Cheese,?> col : definition.columns()) {
                    columns.add(col);
                }

                IDataProvider provider = definition.provider();

                exporter.exportData(provider,columns,outputStream);
            }
        });

        return resourceResponse;
    }
    // method getFeed()…
}