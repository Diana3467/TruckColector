using ServicesWeb.Modelos;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace ServicesWeb.Repositorio
{
    public class CarroRepositorio
    {
        public static List<Carro> ListarCarros()
        {
            List<Carro> oCarro = new List<Carro>();

            string sp = StoredProcedure.USP_LISTAR_CARROS;

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
                            oCarro.Add(new Carro()
                            {
                                nCodigoCar = dr["nCodigoCar"].ToString(),
                                cMarcaCar = dr["cMarcaCar"].ToString(),
                                cAnioCar = dr["cAnioCar"].ToString(),
                                cPlacaCar = dr["cPlacaCar"].ToString(),
                                cAltoCar = dr["cAltoCar"].ToString(),
                                cAnchoCar = dr["cAnchoCar"].ToString(),
                                cLargoCar = dr["cLargoCar"].ToString(),
                                cCapacCar = dr["cCapacCar"].ToString(),
                                lEstadoCar = dr["lEstadoCar"].ToString(),

                            });
                        }

                    }
                    return oCarro;
                }
                catch (Exception ex)
                {
                    return oCarro;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }
    }
}
