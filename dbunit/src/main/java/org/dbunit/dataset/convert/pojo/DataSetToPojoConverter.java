package org.dbunit.dataset.convert.pojo;

import java.util.ArrayList;
import java.util.List;

import org.dbunit.dataset.Column;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableMetaData;

public class DataSetToPojoConverter
{
    <T> List<T> loadDataSetTableIntoEntity(final IDataSet dataSet,
            final String tableName) throws DataSetException
    {
        final List<T> list = new ArrayList<T>();

        final ITable table = dataSet.getTable(tableName);
        final ITableMetaData tableMetaData = table.getTableMetaData();
        final Column[] columns = tableMetaData.getColumns();

        final int rowCount = table.getRowCount();
        for (int rowNum = 0; rowNum < rowCount; rowNum++)
        {
            processRow(table, columns, rowNum);
        }

        return list;
    }

    protected void processRow(final ITable table, final Column[] columns,
            final int rowNum) throws DataSetException
    {
        final int columnCount = columns.length;
        for (int columnNum = 0; columnNum < columnCount; columnNum++)
        {
            processColumn(table, columns, rowNum, columnNum);
        }
    }

    protected void processColumn(final ITable table, final Column[] columns,
            final int rowNum, final int columnNum) throws DataSetException
    {
        final Column column = columns[columnNum];
        final String columnName = column.getColumnName();
        final Object columnValue = table.getValue(rowNum, columnName);
    }
}
