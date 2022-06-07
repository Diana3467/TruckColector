using ServicesWeb.Modelos;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace ServicesWeb.Repositorio
{
    public class CalleZonaRepositorio
    {

        public static List<CalleZona> ListarCalleZona(CalleZona oCalleZona)
        {
            List<CalleZona> oListarCalleZona = new List<CalleZona>();

            string sp = StoredProcedure.USP_LISTAR_CALLES;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {

                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_cDescZona", oCalleZona.cDescZona.ToString());

                try
                {
                    oConexion.Open();

                    using (SqlDataReader dr = parametros.ExecuteReader())
                    {
                        while (dr.Read())
                        {
                            oListarCalleZona.Add(new CalleZona()
                            {
                                nCodigoCalle = dr["nCodigoCalle"].ToString(),
                                cNombreCalle = dr["cNombreCalle"].ToString(),

                            });
                        }

                    }
                    return oListarCalleZona;
                }
                catch
                {
                    return oListarCalleZona;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }















    }
}