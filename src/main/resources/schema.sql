CREATE TABLE IF NOT EXISTS policies (
    id BIGINT NOT NULL AUTO_INCREMENT,  -- 政策ID，自增长
    title_en VARCHAR(255) NOT NULL,      -- 英文标题
    title_cn VARCHAR(255) NOT NULL,      -- 中文标题
    content_en TEXT NOT NULL,            -- 英文内容
    content_cn TEXT NOT NULL,            -- 中文内容
    department VARCHAR(255) NOT NULL,    -- 颁布部门
    publish_date DATE NOT NULL,          -- 发布日期
    vector_id VARCHAR(255),              -- 向量数据库ID
    PRIMARY KEY (id)                     -- 主键是政策ID
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;