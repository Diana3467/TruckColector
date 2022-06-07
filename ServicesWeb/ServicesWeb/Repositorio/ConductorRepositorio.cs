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
    public class ConductorRepositorio
    {
        public static List<Conductor> ListarConductores()
        {
            List<Conductor> oConductor = new List<Conductor>();

            string sp = StoredProcedure.USP_LISTAR_CONDUCTORES;

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
                            oConductor.Add(new Conductor()
                            {
                                nCodigoCond = dr["nCodigoCond"].ToString(),
                                cNombreCond = dr["cNombreCond"].ToString(),
                                cApePatCond = dr["cApePatCond"].ToString(),
                                cApeMatCond = dr["cApeMatCond"].ToString(),
                                cDNICond = dr["cDNICond"].ToString(),
                                cEdadCond = dr["cEdadCond"].ToString(),
                                cCelCond = dr["cCelCond"].ToString(),
                                cDireccCond = dr["cDireccCond"].ToString(),
                                cCorEleCond = dr["cCorEleCond"].ToString(),
                                lEstadoCond = dr["lEstadoCond"].ToString(),

                            });
                        }

                    }
                    return oConductor;
                }
                catch (Exception ex)
                {
                    return oConductor;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }
        public static Token AutentificarConductor(Conductor oConductor)
        {
            string sp = StoredProcedure.USP_AUTENTIFICAR_CONDUCTOR;
            Token oToken = new Token();

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_cDNICondParameter", oConductor.cDNICond.ToString());
                parametros.Parameters.AddWithValue("@x_cPassCondParameter", oConductor.cPassCond.ToString());

                try
                {
                    oConexion.Open();

                    using (SqlDataReader dr = parametros.ExecuteReader())
                    {
                        while (dr.Read())
                        {
                            oToken.cMensajeAut = dr["cMenAutCond"].ToString();
                        }

                    }
                    return oToken;
                }
                catch (IOException e)
                {
                    return oToken;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }

        public static Conductor TraerDatosConductor(string cDNICond)
        {
            string sp = StoredProcedure.USP_TRAER_UNO_CONDUCTOR;
            Conductor oConductor = new Conductor();

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {

                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_cDNICondParameter", cDNICond.ToString());

                try
                {
                    oConexion.Open();

                    using (SqlDataReader dr = parametros.ExecuteReader())
                    {
                        while (dr.Read())
                        {
                            oConductor.nCodigoCond = dr["nCodigoCond"].ToString();
                            oConductor.cNombreCond = dr["cNombreCond"].ToString();
                            oConductor.cApePatCond = dr["cApePatCond"].ToString();
                            oConductor.cApeMatCond = dr["cApeMatCond"].ToString();
                            oConductor.cDNICond = dr["cDNICond"].ToString();
                            oConductor.cEdadCond = dr["cEdadCond"].ToString();
                            oConductor.cCelCond = dr["cCelCond"].ToString();
                            oConductor.cDireccCond = dr["cDireccCond"].ToString();
                            oConductor.cCorEleCond = dr["cCorEleCond"].ToString();
                            oConductor.lEstadoCond = dr["lEstadoCond"].ToString();
                        }

                    }
                    return oConductor;
                }
                catch (IOException e)
                {
                    return oConductor;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }


        public static bool Grabar(Conductor oConductor)
        {
            string sp = StoredProcedure.USP_CONDUCTOR_GRABAR;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                bool respuesta = false;
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_cNombreCond", oConductor.cNombreCond.ToString());
                parametros.Parameters.AddWithValue("@x_cApePatCond", oConductor.cApePatCond.ToString());
                parametros.Parameters.AddWithValue("@x_cApeMatCond", oConductor.cApeMatCond.ToString());
                parametros.Parameters.AddWithValue("@x_cDNICond", oConductor.cDNICond.ToString());
                parametros.Parameters.AddWithValue("@x_cEdadCond", oConductor.cEdadCond.ToString());
                parametros.Parameters.AddWithValue("@x_cCelCond", oConductor.cCelCond.ToString());
                parametros.Parameters.AddWithValue("@x_cDireccCond", oConductor.cDireccCond.ToString());
                parametros.Parameters.AddWithValue("@x_cCorEleCond", oConductor.cCorEleCond.ToString());

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

        public static bool Actualizar(Conductor oConductor)
        {
            string sp = StoredProcedure.USP_ACTUALIZAR_DATOS_CONDUCTOR;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                bool respuesta = false;
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_nCodigoCond", oConductor.nCodigoCond.ToString());
                parametros.Parameters.AddWithValue("@x_cNombreCond", oConductor.cNombreCond.ToString());
                parametros.Parameters.AddWithValue("@x_cApePatCond", oConductor.cApePatCond.ToString());
                parametros.Parameters.AddWithValue("@x_cApeMatCond", oConductor.cApeMatCond.ToString());
                parametros.Parameters.AddWithValue("@x_cDNICond", oConductor.cDNICond.ToString());
                parametros.Parameters.AddWithValue("@x_cEdadCond", oConductor.cEdadCond.ToString());
                parametros.Parameters.AddWithValue("@x_cCelCond", oConductor.cCelCond.ToString());
                parametros.Parameters.AddWithValue("@x_cDireccCond", oConductor.cDireccCond.ToString());
                parametros.Parameters.AddWithValue("@x_cCorEleCond", oConductor.cCorEleCond.ToString());
                parametros.Parameters.AddWithValue("@x_cPassCond", oConductor.cDNICond.ToString());

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

        public void Dispose()
        {
            GC.SuppressFinalize(this);
        }


    }
}