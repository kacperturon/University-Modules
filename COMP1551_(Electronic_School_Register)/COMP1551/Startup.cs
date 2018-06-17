using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(COMP1551.Startup))]
namespace COMP1551
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
