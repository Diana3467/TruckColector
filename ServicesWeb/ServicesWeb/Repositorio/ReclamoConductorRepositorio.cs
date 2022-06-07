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
    public class ReclamoConductorRepositorio
    {


        public static bool InsertarReclamoConductor(ReclamoConductor oReclamoConductor)
        {
            string sp = StoredProcedure.USP_INSERTAR_RECLAMO_CONDUCTOR;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                bool respuesta = false;
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;

                parametros.Parameters.AddWithValue("@x_cFechaRecCo", oReclamoConductor.cFechaRecCo.ToString());
                parametros.Parameters.AddWithValue("@x_cDescripcionRecCo", oReclamoConductor.cDescripcionRecCo.ToString());
                parametros.Parameters.AddWithValue("@x_nCodigoCond", oReclamoConductor.nCodigoCond.ToString());

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

        public static bool CambiarEstadoReclamoConductor(ReclamoConductor oReclamoConductor)
        {
            string sp = StoredProcedure.USP_CAMBIAR_ESTADO_RECLAMO_CONDUCTOR;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                bool respuesta = false;
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;

                parametros.Parameters.AddWithValue("@x_nCodigoRecCo", oReclamoConductor.nCodigoRecCo.ToString());

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



        public static List<ReclamoConductor> ListarReclamosConductores(Filtro oFiltro)
        {
            List<ReclamoConductor> oReclamoConductor = new List<ReclamoConductor>();

            string sp = StoredProcedure.USP_LISTAR_FILTRO_RECLAMO_CONDUCTOR;

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
                            oReclamoConductor.Add(new ReclamoConductor()
                            {
                                nCodigoRecCo = dr["nCodigoRecCo"].ToString(),
                                cFechaRecCo = dr["cFechaRecCo"].ToString(),
                                cDescripcionRecCo = dr["cDescripcionRecCo"].ToString(),
                                lEstadoRecCo = dr["lEstadoRecCo"].ToString(),
                                nCodigoCond = dr["nCodigoCond"].ToString(),
                                cNombreCompleto = dr["cNombreCompleto"].ToString(),

                            });
                        }

                    }
                    return oReclamoConductor;
                }
                catch
                {
                    return oReclamoConductor;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }




    }
}