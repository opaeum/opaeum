package org.nakeduml.hibernate.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.ValueBinder;
import org.hibernate.type.descriptor.ValueExtractor;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaTypeDescriptor;
import org.hibernate.type.descriptor.java.PrimitiveByteArrayTypeDescriptor;
import org.hibernate.type.descriptor.sql.BasicExtractor;
import org.hibernate.type.descriptor.sql.BlobTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

public class PostgresDialect extends PostgreSQLDialect{
	public PostgresDialect(){
		super();
		registerColumnType(Types.BLOB, "bytea");
	}
	public boolean useInputStreamToInsertBlob(){
		return false;
	}
	public static class PostgresqlMateralizedBlobType extends AbstractSingleColumnStandardBasicType<byte[]>{
		public static final PostgresqlMateralizedBlobType INSTANCE = new PostgresqlMateralizedBlobType();
		public PostgresqlMateralizedBlobType(){
			super(PostgresqlBlobTypeDescriptor.INSTANCE, PrimitiveByteArrayTypeDescriptor.INSTANCE);
		}
		public String getName(){
			return "materialized_blob";
		}
	}
	public static class PostgresqlBlobTypeDescriptor extends BlobTypeDescriptor implements SqlTypeDescriptor{
		public static final BlobTypeDescriptor INSTANCE = new PostgresqlBlobTypeDescriptor();
		public <X>ValueBinder<X> getBinder(final JavaTypeDescriptor<X> javaTypeDescriptor){
			return new PostgresqlBlobBinder<X>(javaTypeDescriptor, this);
		}
		public <X>ValueExtractor<X> getExtractor(final JavaTypeDescriptor<X> javaTypeDescriptor){
			return new BasicExtractor<X>(javaTypeDescriptor, this){
				@Override
				public X doExtract(ResultSet s,String name,WrapperOptions options) throws SQLException{
					return (X) s.getBytes(name);
				}
			};
		}
	}
	public static class PostgresqlBlobBinder<J> implements ValueBinder<J>{
		private final JavaTypeDescriptor<J> javaDescriptor;
		private final SqlTypeDescriptor sqlDescriptor;
		public PostgresqlBlobBinder(JavaTypeDescriptor<J> javaDescriptor,SqlTypeDescriptor sqlDescriptor){
			this.javaDescriptor = javaDescriptor;
			this.sqlDescriptor = sqlDescriptor;
		}
		public final void bind(PreparedStatement st,J value,int index,WrapperOptions options) throws SQLException{
			st.setBytes(index, (byte[]) value);
		}
	}
}