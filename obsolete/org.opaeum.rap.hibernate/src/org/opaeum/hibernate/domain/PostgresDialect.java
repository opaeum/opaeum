package org.opaeum.hibernate.domain;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQLDialect;

public class PostgresDialect extends PostgreSQLDialect{
	public PostgresDialect(){
		super();
		registerColumnType(Types.BLOB, "bytea");
	}
	public boolean useInputStreamToInsertBlob(){
		return false;
	}
//	public static class PostgresqlMateralizedBlobType extends AbstractSingleColumnStandardBasicType<byte[]>{
//		private static final long serialVersionUID = -2492144303884252721L;
//		public static final PostgresqlMateralizedBlobType INSTANCE = new PostgresqlMateralizedBlobType();
//		public PostgresqlMateralizedBlobType(){
//			super(PostgresqlBlobTypeDescriptor.INSTANCE, PrimitiveByteArrayTypeDescriptor.INSTANCE);
//		}
//		public String getName(){
//			return "materialized_blob";
//		}
//	}
//	public static class PostgresqlBlobTypeDescriptor extends BlobTypeDescriptor implements SqlTypeDescriptor{
//		private static final long serialVersionUID = -6812000735608753190L;
//		public static final BlobTypeDescriptor INSTANCE = new PostgresqlBlobTypeDescriptor();
//		public <X>ValueBinder<X> getBinder(final JavaTypeDescriptor<X> javaTypeDescriptor){
//			return new PostgresqlBlobBinder<X>(javaTypeDescriptor, this);
//		}
//		public <X>ValueExtractor<X> getExtractor(final JavaTypeDescriptor<X> javaTypeDescriptor){
//			return new BasicExtractor<X>(javaTypeDescriptor, this){
//				@SuppressWarnings("unchecked")
//				@Override
//				public X doExtract(ResultSet s,String name,WrapperOptions options) throws SQLException{
//					return (X) s.getBytes(name);
//				}
//			};
//		}
//	}
//	public static class PostgresqlBlobBinder<J> implements ValueBinder<J>{
//		public PostgresqlBlobBinder(JavaTypeDescriptor<J> javaDescriptor,SqlTypeDescriptor sqlDescriptor){
//		}
//		public final void bind(PreparedStatement st,J value,int index,WrapperOptions options) throws SQLException{
//			st.setBytes(index, (byte[]) value);
//		}
//	}
}