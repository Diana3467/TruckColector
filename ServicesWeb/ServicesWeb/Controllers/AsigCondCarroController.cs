using Microsoft.AspNetCore.Mvc;
using ServicesWeb.Modelos;
using ServicesWeb.Repositorio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace ServicesWeb.Controllers
{
    
    [ApiController]
    public class AsigCondCarroController : ControllerBase
    {
        // GET: api/<AsigCondCarroController>
        [HttpGet]
        [Route("api/[controller]/conductor")]
        public List<Conductor> GetConductor()
        {
            return AsigCondCarroRepositorio.ListarConductorDisponible();
        }

        // GET: api/<AsigCondCarroController>
        [HttpGet]
        [Route("api/[controller]/carro")]
        public List<Carro> GetCarro()
        {
            return AsigCondCarroRepositorio.ListarCarroDisponible();
        }

                            /*// GET api/<AsigCondCarroController>/5
                            [HttpGet("{id}")]
                            public string Get(int id)
                            {
                                return "value";
                            }*/

        // POST api/<AsigCondCarroController>
        [HttpPost]
        [Route("api/[controller]")]
        public bool Post([FromBody] AsigCondCarro oAsigCondCarro)
        {
            return AsigCondCarroRepositorio.Grabar(oAsigCondCarro);
        }

                            /*// PUT api/<AsigCondCarroController>/5
                            [HttpPut("{id}")]
                            public void Put(int id, [FromBody] string value)
                            {
                            }

                            // DELETE api/<AsigCondCarroController>/5
                            [HttpDelete("{id}")]
                            public void Delete(int id)
                            {
                            }*/
    }
}
