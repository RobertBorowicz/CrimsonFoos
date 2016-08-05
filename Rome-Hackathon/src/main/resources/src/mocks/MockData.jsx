export default class MockData {
    static getGames() {
        return (
            [
                {
                    red: {
                        players: ['Craig', 'Graham'],
                        goals: 10
                    },
                    blue: {
                        players: ['Connor', 'Robert'],
                        goals: 8
                    },
                    date: '2016-08-03T19:48:40.707'
                },
                {
                    red: {
                        players: ['Craig', 'Connor'],
                        goals: 7
                    },
                    blue: {
                        players: ['Graham', 'Robert'],
                        goals: 10
                    },
                    date: '2016-08-03T19:48:40.707'
                },
                {
                    red: {
                        players: ['Craig', 'Graham'],
                        goals: 10
                    },
                    blue: {
                        players: ['Connor', 'Robert'],
                        goals: 8
                    },
                    date: '2016-08-03T19:48:40.707'
                },
                {
                    red: {
                        players: ['Craig', 'Connor'],
                        goals: 7
                    },
                    blue: {
                        players: ['Graham', 'Robert'],
                        goals: 10
                    },
                    date: '2016-08-02T19:48:40.707'
                },
                {
                    red: {
                        players: ['Craig', 'Robert'],
                        goals: 10
                    },
                    blue: {
                        players: ['Connor', 'Graham'],
                        goals: 8
                    },
                    date: '2016-08-02T19:48:40.707'
                },
                {
                    red: {
                        players: ['Craig', 'Graham'],
                        goals: 10
                    },
                    blue: {
                        players: ['Connor', 'Robert'],
                        goals: 8
                    },
                    date: '2016-08-01T19:48:40.707'
                },
                {
                    red: {
                        players: ['Craig', 'Connor'],
                        goals: 7
                    },
                    blue: {
                        players: ['Graham', 'Robert'],
                        goals: 10
                    },
                    date: '2016-08-01T19:48:40.707'
                },
                {
                    red: {
                        players: ['Craig', 'Graham'],
                        goals: 10
                    },
                    blue: {
                        players: ['Connor', 'Robert'],
                        goals: 8
                    },
                    date: '2016-07-30T19:48:40.707'
                },
                {
                    red: {
                        players: ['Craig', 'Connor'],
                        goals: 7
                    },
                    blue: {
                        players: ['Graham', 'Robert'],
                        goals: 10
                    },
                    date: '2016-07-30T19:48:40.707'
                },
                {
                    red: {
                        players: ['Craig', 'Robert'],
                        goals: 10
                    },
                    blue: {
                        players: ['Connor', 'Graham'],
                        goals: 8
                    },
                    date: '2016-07-30T19:48:40.707'
                }
            ]
        );
    }

    static getPlayers() {
        return (
            [
                {
                  id: 1,
                  firstName: 'Craig',
                  lastName: 'Walker',
                  nickname: 'Craig W',
                  stats: {
                      games: 241,
                      wins: 132,
                      losses: 109
                  }
                },
                {
                  id: 2,
                  firstName: 'Connor',
                  lastName: 'Dixon',
                  nickname: 'Connor D',
                  stats: {
                      games: 209,
                      wins: 101,
                      losses: 108
                  }
                },
                {
                  id: 3,
                  firstName: 'Graham',
                  lastName: 'Grubbs',
                  nickname: 'Graham G',
                  stats: {
                      games: 384,
                      wins: 92,
                      losses: 292
                  }
                },
                {
                  id: 4,
                  firstName: 'Robert',
                  lastName: 'Borowicz',
                  nickname: 'Robert B',
                  stats: {
                      games: 373,
                      wins: 198,
                      losses: 175
                  }
                },
                {
                  id: 5,
                  firstName: 'Nick',
                  lastName: 'Allen',
                  nickname: 'Nick A',
                  stats: {
                      games: 371,
                      wins: 196,
                      losses: 175
                  }
                },
                {
                  id: 6,
                  firstName: 'Yonus',
                  lastName: 'Kiros',
                  nickname: 'Yonus',
                  stats: {
                      games: 362,
                      wins: 241,
                      losses: 121
                  }
                },
                {
                  id: 7,
                  firstName: 'Benjamin',
                  lastName: 'Padula',
                  nickname: 'Ben P',
                  stats: {
                      games: 463,
                      wins: 391,
                      losses: 72
                  }
                }
            ]
        );
    }

    static getTeams() {
        return (
            [
                {
                    id: 1,
                    players: ['Craig W', 'Robert B'],
                    stats: {
                        games: 50,
                        wins: 35,
                        losses: 15
                    }
                },
                {
                    id: 2,
                    players: ['Connor D', 'Graham G'],
                    stats: {
                        games: 45,
                        wins: 30,
                        losses: 15
                    }
                },
                {
                    id: 3,
                    players: ['Yonus', 'Nick A'],
                    stats: {
                        games: 80,
                        wins: 61,
                        losses: 19
                    }
                },
                {
                    id: 4,
                    players: ['Ben P', 'Yonus'],
                    stats: {
                        games: 95,
                        wins: 78,
                        losses: 17
                    }
                }
            ]
        );
    }
}