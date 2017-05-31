# Middleware provider

How do we insert some middleware that pulls query parameters in
Funcatron?

Here's an example of a Middleware provider that intercepts the
incoming request and pulls out a parameter. Based on the parameter,
the `Map` of information about the request is updated.

So... what does the incoming request `Map` look like? It's
based on the [Clojure Ring](https://github.com/ring-clojure/ring/wiki/Concepts#requests) request, but with `String` key names rather than
Clojure keyword key names.

One can short-circuit the response by returning
a `Map` with [response](https://github.com/ring-clojure/ring/wiki/Concepts#responses) keys (as `String`) and values.
