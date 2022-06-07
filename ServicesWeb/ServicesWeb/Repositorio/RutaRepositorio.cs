using ServicesWeb.Modelos;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace ServicesWeb.Repositorio
{
    public class RutaRepositorio
    {
        public static List<Ruta> ListarRutas()
        {
            List<Ruta> oRuta = new List<Ruta>();

            string sp = StoredProcedure.USP_LISTAR_RUTAS;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {

                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;

                try
                {
                    oConexion.Open();

                    using (SqlDataReader dr = parametros.ExecuteReader())
                    {
                        while (dr.Read())
                        {
                            oRuta.Add(new Ruta()
                            {
                                nCodigoRuta = dr["nCodigoRuta"].ToString(),
                                cNombreRuta = dr["cNombreRuta"].ToString(),
                                cDescripcion = dr["cDescripcion"].ToString(),

                            });
                        }

                    }
                    return oRuta;
                }
                catch (Exception ex)
                {
                    return oRuta;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }
    }
}