using ServicesWeb.Modelos;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace ServicesWeb.Repositorio
{
    public class ReclamoCiudadanoRepositorio
    {

        public static bool InsertarReclamoCiudadano(ReclamoCiudadano oReclamoCiudadano)
        {
            string sp = StoredProcedure.USP_INSERTAR_RECLAMO_CIUDADANO;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                bool respuesta = false;
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;

                parametros.Parameters.AddWithValue("@x_cFechaRecC", oReclamoCiudadano.cFechaRecC.ToString());
                parametros.Parameters.AddWithValue("@x_cDescripcionRecC", oReclamoCiudadano.cDescripcionRecC.ToString());
                parametros.Parameters.AddWithValue("@x_nCodigoCiud", oReclamoCiudadano.nCodigoCiud.ToString());

                try
                {
                    oConexion.Open();
                    parametros.ExecuteNonQuery();

                    respuesta = true;
                    return respuesta;
                }
                catch (IOException e)
                {
                    respuesta = false;
                    return respuesta;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }

        public static bool CambiarEstadoReclamoCiudadano(ReclamoCiudadano oReclamoCiudadano)
        {
            string sp = StoredProcedure.USP_CAMBIAR_ESTADO_RECLAMO_CIUDADANO;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                bool respuesta = false;
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;

                parametros.Parameters.AddWithValue("@x_nCodigoRecC", oReclamoCiudadano.nCodigoRecC.ToString());

                try
                {
                    oConexion.Open();
                    parametros.ExecuteNonQuery();

                    respuesta = true;
                    return respuesta;
                }
                catch (IOException e)
                {
                    respuesta = false;
                    return respuesta;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }



        public static List<ReclamoCiudadano> ListarReclamosCiudadanos(Filtro oFiltro)
        {
            List<ReclamoCiudadano> oReclamoCiudadano = new List<ReclamoCiudadano>();

            string sp = StoredProcedure.USP_LISTAR_FILTRO_RECLAMO_CIUDADANO;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {

                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_filtroNuevo_Visto_Todos", oFiltro.filtroNuevo_Visto_Todos.ToString());

                try
                {
                    oConexion.Open();

                    using (SqlDataReader dr = parametros.ExecuteReader())
                    {
                        while (dr.Read())
                        {
                            oReclamoCiudadano.Add(new ReclamoCiudadano()
                            {
                                nCodigoRecC = dr["nCodigoRecC"].ToString(),
                                cFechaRecC = dr["cFechaRecC"].ToString(),
                                cDescripcionRecC = dr["cDescripcionRecC"].ToString(),
                                lEstadoRecC = dr["lEstadoRecC"].ToString(),
                                nCodigoCiud = dr["nCodigoCiud"].ToString(),
                                cNombreCompleto = dr["cNombreCompleto"].ToString(),

                            });
                        }

                    }
                    return oReclamoCiudadano;
                }
                catch
                {
                    return oReclamoCiudadano;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }







    }
}