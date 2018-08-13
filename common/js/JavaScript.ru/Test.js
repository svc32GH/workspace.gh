describe("pow", function() {

  describe("�������� x � ������� n", function() {

    function makeTest(x) {
      var expected = x * x * x;
      it("��� ���������� " + x + " � ������� 3 ���������: " + expected, function() {
        assert.equal(pow(x, 3), expected);
      });
    }

    for (var x = 1; x <= 5; x++) {
      makeTest(x);
    }
    x = 3.14159;
    makeTest(x);

  });

  describe("�������� ���������� �������� n", function() {

    it("��� ���������� � ������������� ������� ��������� NaN", function() {
      assert(isNaN(pow(2, -1)), "pow(2, -1) �� NaN");
    });

    it("��� ���������� � ������� ������� ��������� NaN", function() {
      assert(isNaN(pow(2, 1.5)), "pow(2, 1.5) �� NaN");
    });

    it("��� ���������� ������ ����� � ������� ��������� (����-� 2.0) ��� ���������� ���-�", function() {
      var expected = pow(2, 2);
      assert(pow(2, 2.0), expected);
    });

  });

  describe("����� �����, ����� ����, � ������� 0 ����� 1", function() {

    function makeTest(x) {
      it("��� ���������� " + x + " � ������� 0 ���������: 1", function() {
        assert.equal(pow(x, 0), 1);
      });
    }

    for (var x = -5; x <= 5; x += 2) {
      makeTest(x);
    }

  });

  it("���� � ������� ������� ��� NaN", function() {
    assert(isNaN(pow(0, 0)), "0 � ������� 0 �� NaN");
  });

  // ... ���������� ����� it � �������� describe ...
});