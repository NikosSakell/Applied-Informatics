# saved as adding-client-NS.py
import Pyro4

a = 5
b = 6

adding_maker = Pyro4.Proxy("PYRONAME:example.adding")    # use name server object lookup uri shortcut
# alternatives for PYRONAME
# PYRONAME:some_logical_object_name
# PYRONAME:some_logical_object_name@nshostname           with optional host name
# PYRONAME:some_logical_object_name@nshostname:nsport    with optional host name + port
print(adding_maker.add(a, b))