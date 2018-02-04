# The Archives of Ragnarok
A Library of Adventures

[![License](https://img.shields.io/badge/license-GPL%20v3-brightgreen.svg)](https://www.gnu.org/licenses/gpl-3.0.txt) [![Build](https://travis-ci.org/sshookman/ProjectRead.svg?branch=master)](https://travis-ci.org/sshookman/ragnarok-hub) [![Coverage](https://codecov.io/gh/sshookman/ProjectRead/branch/master/graph/badge.svg)](https://codecov.io/gh/sshookman/ProjectRead)

---

## Table of Contents

- [About](#about)
  - [Purpose](#purpose)
  - [Plans](#plans)
- [Getting Started](#getting-started)
  - [Running Locally](#running-locally)
  - [Running with Docker](#running-with-docker)
  - [Connecting to the Hub](#connecting-to-the-hub)
- [Contributing](#contributing)

---

## About

The Archives of Ragnarok is a Java-based text-based-adventure hub accessible through Telnet.

#### Purpose

The purpose is to create a single online library filled with various interactive stories and games
that can be accessed from any device capable of establishing a telnet connection. The hub will serve
as the gateway to the stories as well as a portal for authentication and cloud storage of saved game
states. In order to act as a library the hub will also allow for the uploading of stories.

#### Plans

Once in a usable and stable state the hub will be launched on a public server and will be accessible
through telnet on port 1127.

Another plan is create a simple script that would turn a raspberry pi into command-line-only computer for the
sole pupose of accessing the hub. Then you could just shove it into a lunchbox and play in style wherever you go! 
A RagnaBox if you will. Eesh, maybe don't.

A nice-to-have would be a graphical (probably web-based) tool to aid in the construction of games
so people don't have to mess around with sqlite just to make a game. A bit more work than the RagnaBox.

---

## Getting Started

#### Running Locally

The application can be run locally using a single gradle command. The gradle wrapper is included
with the repository, so gradle does not need to be installed in order to execute the command. Wasn't
that nice of me!

```
$ ./gradlew clean run
```

This will initiate the spring boot application on your local machine and will serve the library out
of port 1127 over telnet. Assuming your birthday is not also the 27th of November and you are not already
using that port.

#### Running with Docker

You must have docker installed and running in order to perform the following commands.

```
$ ./scripts/build.sh
$ ./scripts/run.sh
```

Once this is done, the ragnarok docker container will be running and serving the Telnet Server for
the hub out of port 1127. Don't forget to kill (and optionally remove) the container
once you are finished. Or don't, you're free to make whatever terrible decisions you want in life.

#### Connecting to the Hub

Assuming you are running the server on your local machine (or local docker) you can use
the following command to connect to the server. Otherwise, simply change 'localhost' below to the
IP address or hostname of the server on which the hub is running.

```
$ telnet localhost 1127
```

This will connect you to The Archives of Ragnarok where you can log in and start playing stories.

---

## Contributing

Feel free to dive in and contribute! [Open an issue](https://github.com/sshookman/ProjectRead/issues/new) or submit PRs.
This project follows the [Contributor Covenant](http://contributor-covenant.org/version/1/3/0/) Code of Conduct.
