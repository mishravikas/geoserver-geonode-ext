import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sample
{
  public static void main(String[] args) throws ClassNotFoundException
  {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");

    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:/home/vikas/dev/permissions/geonode/geonode/development.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      // statement.executeUpdate("drop table if exists person");
      // statement.executeUpdate("create table person (id integer, name string)");
      // statement.executeUpdate("insert into person values(1, 'leo')");
      // statement.executeUpdate("insert into person values(2, 'yui')");
      ResultSet rs_write = statement.executeQuery("SELECT base_resourcebase.id, base_resourcebase.polymorphic_ctype_id, base_resourcebase.uuid, base_resourcebase.owner_id, base_resourcebase.title, base_resourcebase.date, base_resourcebase.date_type, base_resourcebase.edition, base_resourcebase.abstract, base_resourcebase.purpose, base_resourcebase.maintenance_frequency, base_resourcebase.restriction_code_type_id, base_resourcebase.constraints_other, base_resourcebase.license_id, base_resourcebase.language, base_resourcebase.category_id, base_resourcebase.spatial_representation_type_id, base_resourcebase.temporal_extent_start, base_resourcebase.temporal_extent_end, base_resourcebase.supplemental_information, base_resourcebase.distribution_url, base_resourcebase.distribution_description, base_resourcebase.data_quality_statement, base_resourcebase.bbox_x0, base_resourcebase.bbox_x1, base_resourcebase.bbox_y0, base_resourcebase.bbox_y1, base_resourcebase.srid, base_resourcebase.csw_typename, base_resourcebase.csw_schema, base_resourcebase.csw_mdsource, base_resourcebase.csw_insert_date, base_resourcebase.csw_type, base_resourcebase.csw_anytext, base_resourcebase.csw_wkt_geometry, base_resourcebase.metadata_uploaded, base_resourcebase.metadata_xml, base_resourcebase.thumbnail_id, base_resourcebase.popular_count, base_resourcebase.share_count, base_resourcebase.featured, base_resourcebase.thumbnail_url, base_resourcebase.detail_url, base_resourcebase.rating FROM base_resourcebase");
      while(rs_write.next())
      {
        // read the result set
        System.out.println("name = geonode:" + rs_write.getString("title"));
        // System.out.println("id = " + rs.getInt("id"));
      }
    }
    catch(SQLException e)
    {
      // if the error message is "out of memory", 
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
    }
  }
}