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
    [Route("api/[controller]")]
    [ApiController]
    public class PublicarController : ControllerBase
    {
                            /*// GET: api/<ValuesController>
                            [HttpGet]
                            public IEnumerable<string> Get()
                            {
                                return new string[] { "value1", "value2" };
                            }

                            // GET api/<ValuesController>/5
                            [HttpGet("{id}")]
                            public string Get(int id)
                            {
                                return "value";
                            }*/

        // POST api/<ValuesController>
        [HttpPost]
        public bool Post([FromBody] Publicar oPublicar)
        {
            return PublicarRepositorio.Grabar(oPublicar);
        }

                            /*// PUT api/<ValuesController>/5
                            [HttpPut("{id}")]
                            public void Put(int id, [FromBody] string value)
                            {
                            }

                            // DELETE api/<ValuesController>/5
                            [HttpDelete("{id}")]
                            public void Delete(int id)
                            {
                            }*/
    }
}
