using ServicesWeb.Modelos;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace ServicesWeb.Repositorio
{
    public class AsigRutaCondRepositorio
    {
        public static bool AsignarRutaConductor(AsigRutaCond oAsigRutaCond)
        {
            string sp = StoredProcedure.USP_ASIGNAR_RUTA_CONDUCTOR;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                bool respuesta = false;
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_nCodigoRuta", oAsigRutaCond.nCodigoRuta.ToString());
                parametros.Parameters.AddWithValue("@x_nCodigoCond", oAsigRutaCond.nCodigoCond.ToString());
                parametros.Parameters.AddWithValue("@x_dFechaInicio", oAsigRutaCond.dFechaInicio.ToString());
                parametros.Parameters.AddWithValue("@x_dFechaFin", oAsigRutaCond.dFechaFin.ToString());
                parametros.Parameters.AddWithValue("@x_nCodigoAdm", oAsigRutaCond.nCodigoAdm.ToString());

                try
                {
                    oConexion.Open();
                    parametros.ExecuteNonQuery();

                    respuesta = true;
                    return respuesta;
                }
                catch (Exception ex)
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

        public static List<AsigRutaCond> ListarRutaConductor()
        {
            List<AsigRutaCond> oAsigRutaCond = new List<AsigRutaCond>();

            string sp = StoredProcedure.USP_LISTAR_RUTAS_CONDUCTORES;

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
                            oAsigRutaCond.Add(new AsigRutaCond()
                            {
                                nCodigoRuta = dr["nCodigoRuta"].ToString(),
                                nCodigoCond = dr["nCodigoCond"].ToString(),
                                dFechaInicio = dr["dFechaInicio"].ToString(),
                                dFechaFin = dr["dFechaFin"].ToString(),
                                lEstadoAsigRutaCond = dr["lEstadoAsigRutaCond"].ToString(),
                                nCodigoAdm = dr["nCodigoAdm"].ToString(),
                                cInfoCond = dr["cInfoCond"].ToString(),
                                cInfoRuta = dr["cInfoRuta"].ToString(),

                            });
                        }

                    }
                    return oAsigRutaCond;
                }
                catch (Exception ex)
                {
                    return oAsigRutaCond;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }

        public static List<AsigRutaCond> ListarRutaAsignadaUserConductor(string nCodigoCond)
        {
            List<AsigRutaCond> oAsigRutaCond = new List<AsigRutaCond>();

            string sp = StoredProcedure.USP_LISTAR_RUTAS_ASIGNADAS_CONDUCTORES;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {

                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_nCodigoCond", nCodigoCond.ToString());

                try
                {
                    oConexion.Open();

                    using (SqlDataReader dr = parametros.ExecuteReader())
                    {
                        while (dr.Read())
                        {
                            oAsigRutaCond.Add(new AsigRutaCond()
                            {
                                cInfoRuta = dr["cNombreRuta"].ToString(),
                                cDescriRuta = dr["cDescripcion"].ToString(),
                                cDias = dr["cDias"].ToString(),
                                dFechaInicio = dr["dFechaInicio"].ToString(),
                                dFechaFin = dr["dFechaFin"].ToString(),
                                cHoraInicioHor = dr["cHoraInicioHor"].ToString(),
                                cHoraFinHor = dr["cHoraFinHor"].ToString(),
                            });
                        }

                    }
                    return oAsigRutaCond;
                }
                catch (Exception ex)
                {
                    return oAsigRutaCond;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }



    }
}
