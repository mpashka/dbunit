/*
 *
 * The DbUnit Database Testing Framework
 * Copyright (C)2002-2004, DbUnit.org
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

package org.dbunit.dataset.datatype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.Blob;

/**
 * @author Manuel Laflamme
 * @version $Revision$
 * @since Jan 12, 2004
 */
public class BlobDataType extends BytesDataType
{
    public BlobDataType()
    {
        super("BLOB", Types.BLOB);
    }

    public Object getSqlValue(int column, ResultSet resultSet) throws SQLException, TypeCastException
    {
        Blob value = resultSet.getBlob(column);
        if (value == null || resultSet.wasNull())
        {
            return null;
        }
        return typeCast(value);
    }

    public void setSqlValue(Object value, int column, PreparedStatement statement) throws SQLException, TypeCastException
    {
        statement.setObject(column, typeCast(value),
                DataType.LONGVARBINARY.getSqlType());
    }
}