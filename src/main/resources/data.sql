-- 애플리케이션 시작 시 자동으로 실행될 초기 데이터
-- ON DUPLICATE KEY UPDATE는 이미 데이터가 있을 경우 중복 오류를 방지하고 업데이트합니다.
INSERT INTO regions (id, name, slug) VALUES (1, '서울', 'seoul') ON DUPLICATE KEY UPDATE name='서울', slug='seoul';
INSERT INTO regions (id, name, slug) VALUES (2, '부산', 'busan') ON DUPLICATE KEY UPDATE name='부산', slug='busan';
INSERT INTO regions (id, name, slug) VALUES (3, '천안', 'cheonan') ON DUPLICATE KEY UPDATE name='천안', slug='cheonan';
INSERT INTO regions (id, name, slug) VALUES (4, '대전', 'daejeon') ON DUPLICATE KEY UPDATE name='대전', slug='daejeon';