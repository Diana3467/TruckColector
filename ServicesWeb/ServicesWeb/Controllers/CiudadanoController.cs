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
    public class CiudadanoController : Controller
    {
        // POST api/<CiudadanoController>/autentificarciudadano
        [HttpPost]
        [Route("api/[controller]/autentificarciudadano")]
        public Token AutentificarCiudadano([FromBody] Ciudadano oCiudadano)
        {
            return CiudadanoRepositorio.AutentificarCiudadano(oCiudadano);
        }

        // POST api/<CiudadanoController>/traeruno/{cDNICiud}
        [HttpPost]
        [Route("api/[controller]/traeruno/{cDNICiud}")]
        public Ciudadano TraerDatosConductor(string cDNICiud)
        {
            return CiudadanoRepositorio.TraerDatosCiudadano(cDNICiud);
        }

        // POST api/<CiudadanoController>
        [HttpPost]
        [Route("api/[controller]")]
        public Token Grabar([FromBody] Ciudadano oCiudadano)
        {
            return CiudadanoRepositorio.Grabar(oCiudadano);
        }

        // POST api/<CiudadanoController>/actualizar
        [HttpPost]
        [Route("api/[controller]/actualizar")]
        public bool Actualizar([FromBody] Ciudadano oCiudadano)
        {
            return CiudadanoRepositorio.Actualizar(oCiudadano);
        }

        // POST api/<CiudadanoController>/traerhorario/{nCodigoCalle}
        [HttpPost]
        [Route("api/[controller]/traerhorario/{nCodigoCalle}")]
        public Horario TraerHorarioCiudadano(string nCodigoCalle)
        {
            return CiudadanoRepositorio.TraerHorarioCiudadano(nCodigoCalle);
        }

    }
}
