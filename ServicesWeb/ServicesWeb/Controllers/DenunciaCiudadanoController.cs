using Microsoft.AspNetCore.Mvc;
using ServicesWeb.Modelos;
using ServicesWeb.Repositorio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ServicesWeb.Controllers
{
    [ApiController]
    public class DenunciaCiudadanoController : Controller
    {
        [HttpPost]
        [Route("api/[controller]/listadenuncias")]
        public List<DenunciaCiudadano> ListarDenuncias(Filtro oFiltro)
        {
            return DenunciaCiudadanoRepositorio.ListarDenuncias(oFiltro);
        }

        [HttpPost]
        [Route("api/[controller]/insertar")]
        public bool InsertarDenunciaCiudadano([FromBody] DenunciaCiudadano oDenunciaCiudadano)
        {
            return DenunciaCiudadanoRepositorio.InsertarDenunciaCiudadano(oDenunciaCiudadano);
        }

        [HttpPost]
        [Route("api/[controller]/cambiar")]
        public bool CambiarEstadoDenunciaCiudadano([FromBody] DenunciaCiudadano oDenunciaCiudadano)
        {
            return DenunciaCiudadanoRepositorio.CambiarEstadoDenunciaCiudadano(oDenunciaCiudadano);
        }

        // POST: api/<DenunciaCiudadanoController>/lista/{nCodigoCiud}
        [HttpPost]
        [Route("api/[controller]/lista/{nCodigoCiud}")]
        public List<DenunciaCiudadano> ListarDenunciasUnCiudadano(string nCodigoCiud)
        {
            return DenunciaCiudadanoRepositorio.ListarDenunciasUnCiudadano(nCodigoCiud);
        }

    }
}