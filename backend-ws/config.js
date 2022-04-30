const env = process.env;

const config = {
  db: { /* do not put password or any sensitive info here, done only for demo */
    host: env.DB_HOST || 'heffalump.db.elephantsql.com',
    port: env.DB_PORT || '5432',
    user: env.DB_USER || 'fhdmjxva',
    password: env.DB_PASSWORD || 'w3vlGlT7zdtW25mvdiCduSHsYhbv2ij9',
    database: env.DB_NAME || 'fhdmjxva',
  },
  listPerPage: env.LIST_PER_PAGE || 10,
};

module.exports = config;
