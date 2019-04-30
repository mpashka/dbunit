/*
 *
 * The DbUnit Database Testing Framework
 * Copyright (C)2002-2019, DbUnit.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package org.dbunit.dataset;

import java.io.FileReader;

import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.FlatXmlDataSetTest;

public class TableDecoratorDataSetTest extends AbstractDataSetDecoratorTest
{

    public TableDecoratorDataSetTest(String s)
    {
        super(s);
    }

    @Override
    protected IDataSet createDataSet() throws Exception
    {
        return new TableDecoratorDataSet(
                new FlatXmlDataSetBuilder().build(new FileReader(FlatXmlDataSetTest.DATASET_FILE)),
                t -> new ColumnFilterTable(t, (table, column) -> true));
    }
    
    public void testTableDecoration() throws Exception
    {
        IDataSet dataset = createDataSet();
        
        assertTrue("should be a ColumnFilterTable", dataset.getTable("TEST_TABLE") instanceof ColumnFilterTable);
        assertTrue("should be a FilteredTableMetaData", dataset.getTableMetaData("TEST_TABLE") instanceof FilteredTableMetaData);
        assertTrue("should be a ColumnFilterTable", dataset.getTables()[0] instanceof ColumnFilterTable);
        
        ITableIterator iterator = dataset.iterator();
        iterator.next();
        assertTrue("should be a ColumnFilterTable", iterator.getTable() instanceof ColumnFilterTable);
        assertTrue("should be a FilteredTableMetaData", iterator.getTableMetaData() instanceof FilteredTableMetaData);
        
        iterator = dataset.reverseIterator();
        iterator.next();
        assertTrue("should be a ColumnFilterTable", iterator.getTable() instanceof ColumnFilterTable);
        assertTrue("should be a FilteredTableMetaData", iterator.getTableMetaData() instanceof FilteredTableMetaData);
    }

}
