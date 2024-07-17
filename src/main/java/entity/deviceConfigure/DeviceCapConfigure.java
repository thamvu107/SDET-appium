package entity.deviceConfigure;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class DeviceCapConfigure {
  @NonNull
  private final String platformType;
  @NonNull
  private final String deviceType;
  @NonNull
  private final String id;
  @NonNull
  private final String deviceName;
  @NonNull
  private final String configureFile;
}
