# saved as adding-server-NS.py
# first run python -m Pyro4.naming
import Pyro4

@Pyro4.expose
class addingMaker(object):
    def add(self, a, b):
        return a+b

daemon = Pyro4.Daemon()              # make a Pyro daemon
ns = Pyro4.locateNS()                # find the name server
uri = daemon.register(addingMaker)   # register the greeting maker as a Pyro object
ns.register("example.adding", uri)   # register the object with a name in the name server

print("Ready.")
daemon.requestLoop()                 # start the event loop of the server to wait for calls