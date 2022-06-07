
using ServicesWeb.Modelos;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace ServicesWeb.Repositorio
{
    public class AsigCondCarroRepositorio 
    {
        public static bool Grabar(AsigCondCarro oAsigCondCarro)
        {
            string sp = StoredProcedure.USP_CONDUCTOR_CARRO_ASIGNAR;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                bool respuesta = false;
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_nCodigoCond", oAsigCondCarro.nCodigoCond.ToString());
                parametros.Parameters.AddWithValue("@x_nCodigoCar", oAsigCondCarro.nCodigoCar.ToString());
                parametros.Parameters.AddWithValue("@x_dFechaFin", oAsigCondCarro.dFechaFin.ToString());
                parametros.Parameters.AddWithValue("@x_nCodigoAdm", oAsigCondCarro.nCodigoAdm.ToString());

                try
                {
                    oConexion.Open();
                    parametros.ExecuteNonQuery();

                    respuesta = true;
                    return respuesta;
                }
                catch
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

        public static List<Conductor> ListarConductorDisponible()
        {
            List<Conductor> oListaConductorDisponible = new List<Conductor>();
            string sp = StoredProcedure.USP_CONDUCTOR_DISPONIBLE_LISTAR;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;

                try
                {
                    oConexion.Open();
                    //parametros.ExecuteNonQuery();

                    using (SqlDataReader dr = parametros.ExecuteReader())
                    {
                        while (dr.Read())
                        {
                            oListaConductorDisponible.Add(new Conductor() 
                            {
                            cInfoCond = dr["cInfoCond"].ToString(),
                            //nCodigoCond = Convert.ToInt32(dr["cInfoCond"]),

                            });
                        }

                    }
                    return oListaConductorDisponible;
                }
                catch
                {
                    return oListaConductorDisponible;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }

        public static List<Carro> ListarCarroDisponible()
        {
            List<Carro> oListaCarroDisponible = new List<Carro>();
            string sp = StoredProcedure.USP_CARRO_DISPONIBLE_LISTAR;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {

                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;

                try
                {
                    oConexion.Open();
                    //parametros.ExecuteNonQuery();

                    using (SqlDataReader dr = parametros.ExecuteReader())
                    {
                        while (dr.Read())
                        {
                            oListaCarroDisponible.Add(new Carro()
                            {
                                cInfoCar = dr["cInfoCar"].ToString(),
                                //nCodigoCond = Convert.ToInt32(dr["cInfoCond"]),

                            });
                        }

                    }
                    return oListaCarroDisponible;
                }
                catch
                {
                    return oListaCarroDisponible;
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
